(function () {
    angular.module('characterSheet', [
        'ngRoute'
    ]).config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Character/:id', {
            template: '<character-sheet character="$resolve.character.data"></character-sheet>',
            resolve: {
                character: function ($http, $route) {
                    var format = 'json/character_{0}.json'.format($route.current.params.id);
                    return $http.get(format);
                }
            }
        });

        $routeProvider.otherwise({redirectTo: '/view1'});
    }]);

    angular.module('characterSheet').component('characterSheet', {
        template: '' +
        '<div class="character-sheet">' +
        '<character-info character="$ctrl.character"></character-info>' +
        '<hr>' +
        '<stat-block stats="$ctrl.character.stats"></stat-block>' +
        '</div>',
        bindings: {
            character: '='
        }
    });

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
        '<input id="stat{{stat.name}}" type="number" ng-model="stat.value">' +
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

})();