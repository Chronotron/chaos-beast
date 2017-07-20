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
