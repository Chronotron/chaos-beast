var arrayUtil = {

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

function CharacterItem() {
    this.count = 0;
    this.desc = '';
    this.name = '';
    this.type = CharacterItem.types.MISC;
    this.weight = 0.1;
}

CharacterItem.types = {
    MISC: "Misc",
    WEAPON: "Weapon"
};

CharacterItem.getTypes = function () {
    var types = [];
    for (var prop in CharacterItem.types) {
        if (!CharacterItem.types.hasOwnProperty(prop)) {
            continue;
        }
        types.push(CharacterItem.types[prop]);
    }
    return types
};
(function () {
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

        $routeProvider.otherwise({redirectTo: '/Character/1'});
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
            if (!value) {
                return 0;
            }

            return Math.floor((value - 10) / 2);
        }
    }

})();
(function () {
    angular.module('characterSheet').component('characterItem', {
        controller: CharacterItemController,
        template: '' +
        '<span class="table-cell"><input class="short-text" ng-model="$ctrl.item.name" title="{{$ctrl.item.name}}" ng-maxlength="25"></span>' +
        '<span class="table-cell"><input class="med-text" ng-model="$ctrl.item.desc" title="{{$ctrl.item.desc}}" ng-maxlength="100"></span>' +
        '<span class="table-cell"><select ng-model="$ctrl.item.type" ng-options="itemType for itemType in $ctrl.itemTypes"></select></span>' +
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

        $ctrl.removeItem = removeItem;

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
        '<stat-block stats="$ctrl.character.stats"></stat-block>' +
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

        $ctrl.cancel = cancel;
        $ctrl.saveCharacter = saveCharacter;

        function cancel() {
            CharacterService.getCharacter($ctrl.character.id).then(function (character) {
                $ctrl.character = character;
            });
        }

        function saveCharacter() {
            CharacterService.saveCharacter($ctrl.character).then(function (character) {
                $ctrl.character = character;
            });
        }

    }

})();
