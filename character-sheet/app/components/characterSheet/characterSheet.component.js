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

        $routeProvider.otherwise({redirectTo: '/view1'});
    }]);

    //region Components

    angular.module('characterSheet').component('characterSheet', {
        controller: CharacterSheetController,
        template: '' +
        '<form name="characterForm" class="character-form" ng-submit="$ctrl.saveCharacter()">' +
        '<div class="form-inputs">' +
        '<button type="submit">Save</button>' +
        '<button type="button" ng-click="$ctrl.cancel()">Cancel</button>' +
        '</div>' +
        '<div class="character-sheet">' +
        '<character-info character="$ctrl.character"></character-info>' +
        '<hr>' +
        '<stat-block stats="$ctrl.character.stats"></stat-block>' +
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

        function saveCharacter() {
            CharacterService.saveCharacter($ctrl.character);
        }

        function cancel() {
            CharacterService.getCharacter($ctrl.character.id).then(function (character) {
                $ctrl.character = character;
            });
        }

    }

    angular.module('characterSheet').component('characterInfo', {
        template: '<label for="characterName">Name: </label><input id="characterName" type="text" ng-model="$ctrl.character.name">',
        bindings: {
            character: '='
        }
    });

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
            if(!value) {
                return 0;
            }

            return Math.floor((value - 10) / 2);
        }
    }

    //endregion

    //region Services

    angular.module('characterSheet').service('CharacterService', CharacterService);

    CharacterService.$inject = ['$http', '$q'];
    function CharacterService($http, $q) {
        var service = this;

        var characterIdMap = {};

        service.getCharacter = getCharacter;
        service.saveCharacter = saveCharacter;

        function getCharacter(id) {
            var character = angular.copy(characterIdMap[id]);
            if(character) {
                var deferred = $q.defer();
                deferred.resolve(character);
                return deferred.promise;
            }
            var url = 'json/character_{0}.json'.format(id);
            return $http.get(url).then(function (response) {
                character = response.data;
                if(character) {
                    characterIdMap[character.id] = character;
                }
                return angular.copy(character);
            });
        }

        function saveCharacter(character) {
            characterIdMap[character.id] = angular.copy(character);
        }

    }

    //endregion

})();