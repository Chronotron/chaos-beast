(function () {
    angular.module('characterSheet').service('CharacterService', CharacterService);

    CharacterService.$inject = ['$http', '$q'];

    function CharacterService($http, $q) {
        var service = this;

        var characterIdMap = {};

        service.getCharacter = getCharacter;
        service.saveCharacter = saveCharacter;

        /**
         * @param id
         * @return Promise
         */
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
            return resolveCharacter(character);
        }

    }

})();
