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
