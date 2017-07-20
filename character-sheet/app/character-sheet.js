var arrayUtil = {

    find: function (array, test) {
        for (var i = 0; i < array.length; i++) {
            var obj = array[i];
            if(test(obj, i, array)) {
                return obj;
            }
        }

        return null;
    },

    removeObj: function (array, object) {
        var index = -1;
        for (var i = 0; i < array.length; i++) {
            if (array[i] === object) {
                index = i;
                break;
            }
        }

        array.splice(index, 1);
    },

    sum: function (array, opt_interpreter, initialValue) {
        initialValue = initialValue || 0;
        return array.reduce(function (total, num) {
            return (total || 0) + (opt_interpreter ? opt_interpreter(num) : num);
        }, initialValue);
    }

};

if (!String.prototype.format) {
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] !== 'undefined' ? args[number] : match;
        });
    };
}

var statTypes = {
    STR: 'STR',
    DEX: 'DEX',
    CON: 'CON',
    INT: 'INT',
    WIS: 'WIS',
    CHA: 'CHA'
};

/**
 * @param enumeration
 * @returns {Array}
 */
function getEnumerationToArray(enumeration) {
    var types = [];
    for (var prop in enumeration) {
        if (!enumeration.hasOwnProperty(prop)) {
            continue;
        }
        types.push(enumeration[prop]);
    }
    return types
}

function getStatTypes() {
    return getEnumerationToArray(statTypes);
}var statModifiers = {
    /**
     * @param value
     * @return {number}
     */
    calculateMod: function (value) {
        if (!value) {
            return 0;
        }

        return Math.floor((value - 10) / 2);
    }
};/**
 * @constructor
 * @property {number} count
 * @property {number} weight
 * @property {string} desc
 * @property {string} name
 * @property {string} type
 */
function CharacterItem() {
    this.count = 1;
    this.desc = '';
    this.name = '';
    this.options = null;
    this.type = CharacterItem.types.MISC;
    this.weight = 0.1;
}

CharacterItem.types = {
    MISC: "Misc",
    WEAPON: "Weapon"
};

/**
 * @returns {Array}
 */
CharacterItem.getTypes = function () {
    return getEnumerationToArray(CharacterItem.types);
};
/**
 *
 * @param {number} count
 * @param {number} sides
 * @constructor
 * @property {number} count
 * @property {number} sides
 */
function Die(count, sides) {
    this.count = Math.ceil(count || 1);
    this.sides = Math.ceil(sides || 6);
}

Die.prototype.roll = function () {
    var total = 0;
    var min = 1;
    var max = Math.floor(this.sides);
    for (var i = 0; i < this.count; i++) {
        total += Math.floor(Math.random() * (max - min + 1)) + min;
    }
    return total;
};/**
 * @constructor
 * @property {Die} die
 * @property {number} modifier
 * @property {string} statType
 */
function WeaponOptions() {
    this.die = new Die();
    this.modifier = 0;
    this.statType = statTypes.STR;
}(function () {
    angular.module('characterSheet', [
        'ngRoute'
    ]).config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Character/:id', {
            template: '<character-sheet character="$resolve.information.character" selected-id="$resolve.information.selectedId"></character-sheet>',
            resolve: {
                information: function (CharacterService, $route) {
                    return CharacterService.getInformation($route.current.params.id);
                }
            }
        });
    }]);
})();
(function () {
    angular.module('characterSheet').service('CharacterService', CharacterService);

    CharacterService.$inject = ['$http', '$q'];

    function CharacterService($http, $q) {
        var service = this;

        var characterIdMap = {};
        var characterOptions = [];

        //region Public Interface

        service.getCharacter = getCharacter;
        service.getCharacterOptions = getCharacterOptions;
        service.getInformation = getInformation;
        service.saveCharacter = saveCharacter;

        //endregion

        //region Behavior

        function createCharacterOptions() {
            characterOptions.length = 0;

            var henry = characterIdMap['1'] || {id: '1', name: 'Henry Smelter'};
            var alton = characterIdMap['2'] || {id: '2', name: 'Alton Spoons'};

            characterOptions.push(henry);
            characterOptions.push(alton);
        }

        /**
         * @param id
         * @return Promise
         */
        function getCharacter(id) {
            createCharacterOptions();
            var character = angular.copy(characterIdMap[id]);
            if (character) {
                return resolveCharacter(character);
            }
            var url = 'json/character_{0}.json'.format(id);
            return $http.get(url).then(function (response) {
                character = response.data;
                if (character) {
                    characterIdMap[character.id] = character;
                }
                return angular.copy(character);
            }, function (error) {
                console.error(error.message);
                return null;
            });
        }

        function getCharacterOptions() {
            return characterOptions;
        }

        /**
         * gets the initial information for a view
         * @param id
         * @return Promise
         */
        function getInformation(id) {
            var information = {
                selectedId: id
            };
            return getCharacter(id).then(function (character) {
                information.character = character;
                return information;
            });
        }

        /**
         *
         * @param character
         * @return Promise
         */
        function resolveCharacter(character) {
            var deferred = $q.defer();
            deferred.resolve(character);
            return deferred.promise;
        }

        function saveCharacter(character) {
            character.lastUpdated = new Date().toISOString();
            characterIdMap[character.id] = angular.copy(character);
            createCharacterOptions();
            return resolveCharacter(character);
        }

        //endregion

    }

})();
(function () {
    angular.module('characterSheet').component('characterInfo', {
        template: '<label for="characterName">Name: </label><input id="characterName" ng-model="$ctrl.character.name" ng-maxlength="200">',
        bindings: {
            character: '='
        }
    });
})();
(function () {
    angular.module('characterSheet').component('characterInventory', {
        controller: CharacterInventoryController,
        templateUrl: 'components/characterSheet/characterInventory/characterInventory.component.html',
        bindings: {
            inventory: '='
        }
    });

    function CharacterInventoryController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.addItem = addItem;
        $ctrl.getTotalWeight = getTotalWeight;

        //endregion

        //region Behavior

        function addItem() {
            $ctrl.inventory.items.unshift(new CharacterItem());
        }

        /**
         * returns the formatted total weight of all items in the inventory
         * @returns {string}
         */
        function getTotalWeight() {
            var sum = arrayUtil.sum($ctrl.inventory.items, function (item) {
                return item.weight * item.count;
            });
            return (Math.round(sum * 100) / 100).toFixed(2);
        }

        //endregion

    }

})();
(function () {
    angular.module('characterSheet').component('statBlock', {
        controller: StatBlockController,
        template: '' +
        '<div class="stat-block" ng-repeat="stat in $ctrl.stats">' +
        '<label class="stat-name" for="stat{{stat.name}}" ng-bind="stat.name"></label>' +
        '<input id="stat{{stat.name}}" type="number" ng-model="stat.value" min="1" max="20" required>' +
        '<label class="stat-mod">Mod: {{$ctrl.calculateMod(stat.value)}}</label>' +
        '</div>',
        bindings: {
            stats: '='
        }
    });

    function StatBlockController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.calculateMod = calculateMod;

        //endregion

        //region Behavior

        /**
         * @param value
         * @return {*|number}
         */
        function calculateMod(value) {
            return statModifiers.calculateMod(value);
        }

        //endregion
    }

})();
(function () {
    angular.module('characterSheet').component('weaponBlock', {
        controller: WeaponBlockController,
        templateUrl: 'components/characterSheet/weaponBlock/weaponBlock.component.html',
        bindings: {
            stats: '<',
            weapons: '<'
        }
    });

    function WeaponBlockController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.statTypes = getStatTypes();

        $ctrl.getStat = getStat;
        $ctrl.getStatMod = getStatMod;

        //endregion

        //region Behavior

        function getStat(options) {
            return arrayUtil.find($ctrl.stats, function (stat) {
                return stat.name === options.statType;
            });
        }

        /**
         * gets a formatted stat modifier
         * @param options
         * @return {string}
         */
        function getStatMod(options) {
            var stat = getStat(options);
            if(!stat) {
                return '';
            }

            var calculatedMod = statModifiers.calculateMod(stat.value);
            if(!calculatedMod) {
                return '';
            }

            var sign = calculatedMod >= 0 ? '+' : '-';
            return '{0} {1}'.format(sign, Math.abs(calculatedMod));
        }

        //endregion
    }
})();(function () {
    angular.module('characterSheet').component('weaponDie', {
        controller: WeaponDieController,
        template: '<button type="button" class="die-btn" ng-click="$ctrl.roll()">Roll</button>' +
        '<div class="die-results-container" ng-show="$ctrl.showResults">' +
        '<p class="die-results" ng-bind="$ctrl.results"></p>' +
        '<p class="die-roll-options">' +
        '<button type="button" class="confirm-btn" ng-click="$ctrl.roll()">Re-Roll</button>' +
        '<button type="button" ng-click="$ctrl.showResults = false">Close</button>' +
        '</p>' +
        '</div>',
        bindings: {
            stat: '<',
            weapon: '<'
        }
    });

    function WeaponDieController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.results = '';
        $ctrl.showResults = false;

        $ctrl.roll = roll;

        //endregion

        //region Behavior

        /**
         * generates and shows roll results
         */
        function roll() {
            $ctrl.results = '';
            var stat = $ctrl.stat;
            var weapon = $ctrl.weapon;

            if(!stat || !weapon) {
                return;
            }

            var results = Die.prototype.roll.call(weapon.options.die);
            var calculatedMod = statModifiers.calculateMod(stat.value);
            var weaponMod = weapon.options.modifier;
            var total = results + calculatedMod + weaponMod;

            var weaponModDesc = getCalculationDescription(weaponMod, 'Bonus');
            var calculatedModDesc = getCalculationDescription(calculatedMod, $ctrl.stat.name);
            var calculation = '';

            var calculations = [results];
            if(weaponModDesc) {
                calculations.push(weaponModDesc);
            }

            if(calculatedModDesc) {
                calculations.push(calculatedModDesc);
            }

            if(calculations.length > 1) {
                calculation = ' ({0})'.format(calculations.join(' + '));
            }

            $ctrl.results = '{0}: {1} damage{2}'.format(weapon.name, total, calculation);
            $ctrl.showResults = true;
        }

        /**
         * gets a formatted description of a modifier to the roll
         * @param value
         * @param name
         * @return {string}
         */
        function getCalculationDescription(value, name) {
            return value ? '{0} [{1}]'.format(value, name) : '';
        }

        //endregion

    }
})();(function () {
    angular.module('characterSheet').component('characterItem', {
        controller: CharacterItemController,
        template: '' +
        '<span class="table-cell"><input class="short-text" ng-model="$ctrl.item.name" title="{{$ctrl.item.name}}" ng-maxlength="25"></span>' +
        '<span class="table-cell fill"><input class="med-text" ng-model="$ctrl.item.desc" title="{{$ctrl.item.desc}}" ng-maxlength="100"></span>' +
        '<span class="table-cell"><select ng-model="$ctrl.item.type" ng-options="itemType for itemType in $ctrl.itemTypes" ng-change="$ctrl.onTypeChange()"></select></span>' +
        '<span class="table-cell"><input type="number" class="xshort-text" ng-model="$ctrl.item.count" min="0" max="999" step="1"></span>' +
        '<span class="table-cell"><input type="number" class="xshort-text" ng-model="$ctrl.item.weight" min="0" max="999" step="0.01"></span>' +
        '<span class="table-cell">' +
        '<button type="button" class="remove-btn" ng-click="$ctrl.removeItem()">X</button>' +
        '</span>',
        bindings: {
            inventory: '=',
            item: '='
        }
    });

    function CharacterItemController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.itemTypes = CharacterItem.getTypes();

        $ctrl.onTypeChange = onTypeChange;
        $ctrl.removeItem = removeItem;

        //endregion

        //region Behavior

        /**
         * configures options when type changes
         */
        function onTypeChange() {
            var item = $ctrl.item;
            if (!item.type === CharacterItem.types.WEAPON) {
                item.options = null;
                return;
            }
            item.options = new WeaponOptions();
        }

        function removeItem() {
            if (!$ctrl.inventory) {
                return;
            }

            arrayUtil.removeObj($ctrl.inventory.items, $ctrl.item)
        }

        //endregion
    }

})();
(function () {
    angular.module('characterSheet').component('characterSheet', {
        controller: CharacterSheetController,
        templateUrl: 'components/characterSheet/characterSheet.component.html',
        bindings: {
            character: '=',
            selectedId: '='
        }
    });

    CharacterSheetController.$inject = ['CharacterService'];

    function CharacterSheetController(CharacterService) {
        var $ctrl = this;

        var weapons = [];

        //region Public Interface

        $ctrl.cancel = cancel;
        $ctrl.getCharacter = getCharacter;
        $ctrl.getCharacterOptions = getCharacterOptions;
        $ctrl.getWeapons = getWeapons;
        $ctrl.saveCharacter = saveCharacter;

        //endregion

        //region Behavior

        function cancel() {
            getCharacter();
        }

        function getCharacter(id) {
            id = id || $ctrl.character.id;
            CharacterService.getCharacter(id).then(setCharacter);
        }

        function getCharacterOptions() {
            return CharacterService.getCharacterOptions();
        }

        function getWeapons() {
            weapons.length = 0;
            $ctrl.character.inventory.items.forEach(function (item) {
                if (item.type === CharacterItem.types.WEAPON) {
                    weapons.push(item);
                }
            });
            return weapons;
        }

        function saveCharacter() {
            CharacterService.saveCharacter($ctrl.character).then(setCharacter);
        }

        function setCharacter(character) {
            $ctrl.character = character;
        }

        //endregion

    }

})();
