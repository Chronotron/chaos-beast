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
