(function () {
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

        //region Public Interface

        $ctrl.calculateMod = calculateMod;

        //endregion

        //region Behavior

        /**
         * @param value
         * @return {*|number}
         */
        function calculateMod(value) {
            return statModifiers.calculateMod(value);
        }

        //endregion
    }

})();
