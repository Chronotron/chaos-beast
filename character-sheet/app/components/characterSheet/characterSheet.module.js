(function () {
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
