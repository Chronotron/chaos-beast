(function () {
    angular.module('characterSheet').service('CharacterService', CharacterService);

    CharacterService.$inject = ['$http', '$q'];

    function CharacterService($http, $q) {
        var service = this;

        var characterIdMap = {};
        var characterOptions = [];

        //region Public Interface

        service.getCharacter = getCharacter;
        service.getCharacterOptions = getCharacterOptions;
        service.getInformation = getInformation;
        service.saveCharacter = saveCharacter;

        //endregion

        //region Behavior

        function createCharacterOptions() {
            characterOptions.length = 0;

            var henry = characterIdMap['1'] || {id: '1', name: 'Henry Smelter'};
            var alton = characterIdMap['2'] || {id: '2', name: 'Alton Spoons'};

            characterOptions.push(henry);
            characterOptions.push(alton);
        }

        /**
         * @param id
         * @return Promise
         */
        function getCharacter(id) {
            createCharacterOptions();
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
            }, function (error) {
                console.error(error.message);
                return null;
            });
        }

        function getCharacterOptions() {
            return characterOptions;
        }

        /**
         * gets the initial information for a view
         * @param id
         * @return Promise
         */
        function getInformation(id) {
            var information = {
                selectedId: id
            };
            return getCharacter(id).then(function (character) {
                information.character = character;
                return information;
            });
        }

        /**
         *
         * @param character
         * @return Promise
         */
        function resolveCharacter(character) {
            var deferred = $q.defer();
            deferred.resolve(character);
            return deferred.promise;
        }

        function saveCharacter(character) {
            character.lastUpdated = new Date().toISOString();
            characterIdMap[character.id] = angular.copy(character);
            createCharacterOptions();
            return resolveCharacter(character);
        }

        //endregion

    }

})();
