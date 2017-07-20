(function () {
    angular.module('characterSheet').component('weaponDie', {
        controller: WeaponDieController,
        template: '<button type="button" class="die-btn" ng-click="$ctrl.roll()">Roll</button>' +
        '<div class="die-results-container" ng-show="$ctrl.showResults">' +
        '<p class="die-results" ng-bind="$ctrl.results"></p>' +
        '<p class="die-roll-options">' +
        '<button type="button" class="confirm-btn" ng-click="$ctrl.roll()">Re-Roll</button>' +
        '<button type="button" ng-click="$ctrl.showResults = false">Close</button>' +
        '</p>' +
        '</div>',
        bindings: {
            stat: '<',
            weapon: '<'
        }
    });

    function WeaponDieController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.results = '';
        $ctrl.showResults = false;

        $ctrl.roll = roll;

        //endregion

        //region Behavior

        /**
         * generates and shows roll results
         */
        function roll() {
            $ctrl.results = '';
            var stat = $ctrl.stat;
            var weapon = $ctrl.weapon;

            if(!stat || !weapon) {
                return;
            }

            var results = Die.prototype.roll.call(weapon.options.die);
            var calculatedMod = statModifiers.calculateMod(stat.value);
            var weaponMod = weapon.options.modifier;
            var total = results + calculatedMod + weaponMod;

            var weaponModDesc = getCalculationDescription(weaponMod, 'Bonus');
            var calculatedModDesc = getCalculationDescription(calculatedMod, $ctrl.stat.name);
            var calculation = '';

            var calculations = [results];
            if(weaponModDesc) {
                calculations.push(weaponModDesc);
            }

            if(calculatedModDesc) {
                calculations.push(calculatedModDesc);
            }

            if(calculations.length > 1) {
                calculation = ' ({0})'.format(calculations.join(' + '));
            }

            $ctrl.results = '{0}: {1} damage{2}'.format(weapon.name, total, calculation);
            $ctrl.showResults = true;
        }

        /**
         * gets a formatted description of a modifier to the roll
         * @param value
         * @param name
         * @return {string}
         */
        function getCalculationDescription(value, name) {
            return value ? '{0} [{1}]'.format(value, name) : '';
        }

        //endregion

    }
})();