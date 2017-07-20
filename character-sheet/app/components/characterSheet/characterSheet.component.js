(function () {
    angular.module('characterSheet').component('characterSheet', {
        controller: CharacterSheetController,
        template: '<div class="error-message" ng-if="!$ctrl.character"">An error occurred retrieving the requested information</div>' +
        '<form name="characterForm" class="character-form" ng-submit="$ctrl.saveCharacter()" ng-if="$ctrl.character">' +
        '<div class="form-inputs">' +
        '<select ng-model="$ctrl.selectedId" ng-options="option.id as option.name for option in $ctrl.getCharacterOptions()"' +
        ' ng-change="$ctrl.getCharacter($ctrl.selectedId)"></select>' +
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
            character: '=',
            selectedId: '='
        }
    });

    CharacterSheetController.$inject = ['CharacterService'];

    function CharacterSheetController(CharacterService) {
        var $ctrl = this;

        var weapons = [];

        $ctrl.cancel = cancel;
        $ctrl.getCharacter = getCharacter;
        $ctrl.getCharacterOptions = getCharacterOptions;
        $ctrl.getWeapons = getWeapons;
        $ctrl.saveCharacter = saveCharacter;

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

    }

})();
