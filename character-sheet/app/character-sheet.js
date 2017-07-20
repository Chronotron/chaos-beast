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
    calculateMod: function (value) {
        if (!value) {
            return 0;
        }

        return Math.floor((value - 10) / 2);
    }
};function CharacterItem() {
    this.count = 0;
    this.desc = '';
    this.name = '';
    this.type = CharacterItem.types.MISC;
    this.weight = 0.1;
    this.options = null;
}

CharacterItem.types = {
    MISC: "Misc",
    WEAPON: "Weapon"
};

CharacterItem.getTypes = function () {
    return getEnumerationToArray(CharacterItem.types);
};
function Die(count, sides) {
    this.count = Math.floor(count || 1);
    this.sides = Math.floor(sides || 6);
}

Die.prototype.roll = function () {
    var total = 0;
    var min = 1;
    var max = Math.floor(this.sides);
    for (var i = 0; i < this.count; i++) {
        total += Math.floor(Math.random() * (max - min + 1)) + min;
    }
    return total;
};function WeaponOptions() {
    this.statType = statTypes.STR;
    this.die = new Die();
    this.modifier = 0;
}(function () {
    angular.module('characterSheet', [
        'ngRoute'
    ]).config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Character/:id', {
            template: '<character-sheet character="$resolve.character"></character-sheet>',
            resolve: {
                character: function (CharacterService, $route) {
                    return CharacterService.getCharacter($route.current.params.id);
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

        service.getCharacter = getCharacter;
        service.saveCharacter = saveCharacter;

        function getCharacter(id) {
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
            });
        }

        function resolveCharacter(character) {
            var deferred = $q.defer();
            deferred.resolve(character);
            return deferred.promise;
        }

        function saveCharacter(character) {
            character.lastUpdated = new Date().toISOString();
            characterIdMap[character.id] = angular.copy(character);
            return resolveCharacter(character);
        }

    }

})();
(function () {
    angular.module('characterSheet').component('characterInfo', {
        template: '<label for="characterName">Name: </label><input id="characterName" ng-model="$ctrl.character.name">',
        bindings: {
            character: '='
        }
    });
})();
(function () {
    angular.module('characterSheet').component('characterInventory', {
        controller: CharacterInventoryController,
        template: '' +
        '<div class="inventory-controls">' +
        '<button class="add-btn" type="button" ng-click="$ctrl.addItem()">+</button>' +
        '</div>' +
        '<div class="character-inventory table">' +
        '<div class="table-row">' +
        '<span class="table-header">Name</span>' +
        '<span class="table-header">Desc</span>' +
        '<span class="table-header">Type</span>' +
        '<span class="table-header">No.</span>' +
        '<span class="table-header">Weight</span>' +
        '</div>' +
        '<character-item class="table-row" ng-repeat="item in $ctrl.inventory.items" item="item" inventory="$ctrl.inventory"></character-item>' +
        '<div class="table-caption">Total Weight: {{$ctrl.getTotalWeight()}}</div>' +
        '</div>',
        bindings: {
            inventory: '='
        }
    });

    function CharacterInventoryController() {
        var $ctrl = this;

        $ctrl.addItem = addItem;
        $ctrl.getTotalWeight = getTotalWeight;

        function addItem() {
            $ctrl.inventory.items.unshift(new CharacterItem());
        }

        function getTotalWeight() {
            var sum = arrayUtil.sum($ctrl.inventory.items, function (item) {
                return item.weight * item.count;
            });
            return (Math.round(sum * 100) / 100).toFixed(2);
        }

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

        $ctrl.calculateMod = calculateMod;

        function calculateMod(value) {
            return statModifiers.calculateMod(value);
        }
    }

})();
(function () {
    angular.module('characterSheet').component('weaponBlock', {
        controller: WeaponBlockController,
        template: '' +
        '<div class="table weapon-block">' +
        '<div class="table-row">' +
        '<label class="table-header">Name</label>' +
        '<label class="table-header">Stat</label>' +
        '<label class="table-header">Damage</label>' +
        '</div>' +
        '<div class="table-row" ng-hide="$ctrl.weapons.length">' +
        '<label class="table-cell empty-table">No Weapons</label>' +
        '</div>' +
        '<div class="table-row" ng-repeat="weapon in $ctrl.weapons">' +
        '<label class="table-cell" ng-bind="weapon.name"></label>' +
        '<label>' +
        '<select ng-model="weapon.options.statType" ng-options="statType for statType in $ctrl.statTypes"></select>' +
        '</label>' +
        '<label class="table-cell">' +
        '<input type="number" class="xshort-text" min="1" max="100" ng-model="weapon.options.die.count">' +
        '<label class="phrase">d</label>' +
        '<input type="number" class="xshort-text" min="1" max="100" ng-model="weapon.options.die.sides">' +
        '<label class="phrase">+</label>' +
        '<input type="number" class="xshort-text" min="0" max="100" ng-model="weapon.options.modifier">' +
        '<label class="phrase" ng-bind="$ctrl.getStatMod(weapon.options)"></label>' +
        '</label>' +
        '<label class="table-cell"><weapon-die weapon="weapon" stat="$ctrl.getStat(weapon.options)"></weapon-die></label>' +
        '</div>' +
        '</div>',
        bindings: {
            stats: '<',
            weapons: '<'
        }
    });

    function WeaponBlockController() {
        var $ctrl = this;

        $ctrl.statTypes = getStatTypes();

        $ctrl.getStat = getStat;
        $ctrl.getStatMod = getStatMod;

        function getStat(options) {
            return arrayUtil.find($ctrl.stats, function (stat) {
                return stat.name === options.statType;
            });
        }

        function getStatMod(options) {
            var stat = getStat(options);
            if(!stat) {
                return '';
            }

            var calculatedMod = statModifiers.calculateMod(stat.value);
            if(!calculatedMod) {
                return '';
            }

            return '+ {0}'.format(calculatedMod);
        }
    }
})();(function () {
    angular.module('characterSheet').component('weaponDie', {
        controller: WeaponDieController,
        template: '<button type="button" class="die-btn" ng-click="$ctrl.roll()">Roll</button>' +
        '<div class="die-results-container" ng-show="$ctrl.showResults">' +
        '<p class="die-results" ng-bind="$ctrl.results"></p>' +
        '<button type="button" class="confirm-btn" ng-click="$ctrl.roll()">Re-Roll</button>' +
        '<button type="button" ng-click="$ctrl.showResults = false">Close</button>' +
        '</div>',
        bindings: {
            stat: '<',
            weapon: '<'
        }
    });

    function WeaponDieController() {
        var $ctrl = this;

        $ctrl.results = '';
        $ctrl.showResults = false;

        $ctrl.roll = roll;

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

        function getCalculationDescription(value, name) {
            return value ? '{0} [{1}]'.format(value, name) : '';
        }

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

        $ctrl.itemTypes = CharacterItem.getTypes();

        $ctrl.onTypeChange = onTypeChange;
        $ctrl.removeItem = removeItem;

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
    }

})();
(function () {
    angular.module('characterSheet').component('characterSheet', {
        controller: CharacterSheetController,
        template: '' +
        '<form name="characterForm" class="character-form" ng-submit="$ctrl.saveCharacter()">' +
        '<div class="form-inputs">' +
        '<label class="last-updated" ng-if="$ctrl.character.lastUpdated">Last Saved: {{$ctrl.character.lastUpdated | date : "medium" }}</label>' +
        '<button>Save</button>' +
        '<button type="button" ng-click="$ctrl.cancel()">Cancel</button>' +
        '</div>' +
        '<div class="character-sheet">' +
        '<character-info character="$ctrl.character"></character-info>' +
        '<hr>' +
        '<div class="content-block">' +
        '<stat-block class="content-item-double" stats="$ctrl.character.stats"></stat-block>' +
        '<weapon-block class="content-item" stats="$ctrl.character.stats" weapons="$ctrl.getWeapons()"></weapon-block>' +
        '</div>' +
        '<hr>' +
        '<character-inventory inventory="$ctrl.character.inventory"></character-inventory>' +
        '</div>' +
        '</form>',
        bindings: {
            character: '='
        }
    });

    CharacterSheetController.$inject = ['CharacterService'];

    function CharacterSheetController(CharacterService) {
        var $ctrl = this;

        var weapons = [];

        $ctrl.cancel = cancel;
        $ctrl.getWeapons = getWeapons;
        $ctrl.saveCharacter = saveCharacter;

        function cancel() {
            CharacterService.getCharacter($ctrl.character.id).then(function (character) {
                $ctrl.character = character;
            });
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
            CharacterService.saveCharacter($ctrl.character).then(function (character) {
                $ctrl.character = character;
            });
        }

    }

})();
