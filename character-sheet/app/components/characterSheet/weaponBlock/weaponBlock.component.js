(function () {
    angular.module('characterSheet').component('weaponBlock', {
        controller: WeaponBlockController,
        templateUrl: 'components/characterSheet/weaponBlock/weaponBlock.component.html',
        bindings: {
            stats: '<',
            weapons: '<'
        }
    });

    function WeaponBlockController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.statTypes = getStatTypes();

        $ctrl.getStat = getStat;
        $ctrl.getStatMod = getStatMod;

        //endregion

        //region Behavior

        function getStat(options) {
            return arrayUtil.find($ctrl.stats, function (stat) {
                return stat.name === options.statType;
            });
        }

        /**
         * gets a formatted stat modifier
         * @param options
         * @return {string}
         */
        function getStatMod(options) {
            var stat = getStat(options);
            if(!stat) {
                return '';
            }

            var calculatedMod = statModifiers.calculateMod(stat.value);
            if(!calculatedMod) {
                return '';
            }

            var sign = calculatedMod >= 0 ? '+' : '-';
            return '{0} {1}'.format(sign, Math.abs(calculatedMod));
        }

        //endregion
    }
})();