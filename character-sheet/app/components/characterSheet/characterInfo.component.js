(function () {
    angular.module('characterSheet').component('characterInfo', {
        template: '<label for="characterName">Name: </label><input id="characterName" ng-model="$ctrl.character.name" ng-maxlength="200">',
        bindings: {
            character: '='
        }
    });
})();
