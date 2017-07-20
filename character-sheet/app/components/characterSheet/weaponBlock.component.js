(function () {
    angular.module('characterSheet').component('weaponBlock', {
        controller: WeaponBlockController,
        template: '' +
        '<div class="table weapon-block">' +
        '<div class="table-row">' +
        '<label class="table-header">Name</label>' +
        '<label class="table-header">Stat</label>' +
        '<label class="table-header">Damage</label>' +
        '</div>' +
        '<div class="table-row" ng-hide="$ctrl.weapons.length">' +
        '<label class="table-cell empty-table">No Weapons</label>' +
        '</div>' +
        '<div class="table-row" ng-repeat="weapon in $ctrl.weapons">' +
        '<label class="table-cell" ng-bind="weapon.name"></label>' +
        '<label>' +
        '<select ng-model="weapon.options.statType" ng-options="statType for statType in $ctrl.statTypes"></select>' +
        '</label>' +
        '<label class="table-cell">' +
        '<input type="number" class="xshort-text" min="1" max="100" ng-model="weapon.options.die.count">' +
        '<label class="phrase">d</label>' +
        '<input type="number" class="xshort-text" min="1" max="100" ng-model="weapon.options.die.sides">' +
        '<label class="phrase">+</label>' +
        '<input type="number" class="xshort-text" min="0" max="100" ng-model="weapon.options.modifier">' +
        '<label class="phrase" ng-bind="$ctrl.getStatMod(weapon.options)"></label>' +
        '</label>' +
        '</div>' +
        '</div>',
        bindings: {
            stats: '<',
            weapons: '<'
        }
    });

    function WeaponBlockController() {
        var $ctrl = this;

        $ctrl.statTypes = getStatTypes();

        $ctrl.getStatMod = getStatMod;

        function getStatMod(options) {
            var stat = arrayUtil.find($ctrl.stats, function (stat) {
                return stat.name === options.statType;
            });

            if(!stat) {
                return '';
            }

            var calculatedMod = statModifiers.calculateMod(stat.value);
            if(!calculatedMod) {
                return '';
            }

            return '+ {0}'.format(calculatedMod);
        }
    }
})();