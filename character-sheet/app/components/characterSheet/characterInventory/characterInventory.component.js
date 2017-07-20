(function () {
    angular.module('characterSheet').component('characterInventory', {
        controller: CharacterInventoryController,
        templateUrl: 'components/characterSheet/characterInventory/characterInventory.component.html',
        bindings: {
            inventory: '='
        }
    });

    function CharacterInventoryController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.addItem = addItem;
        $ctrl.getTotalWeight = getTotalWeight;

        //endregion

        //region Behavior

        function addItem() {
            $ctrl.inventory.items.unshift(new CharacterItem());
        }

        /**
         * returns the formatted total weight of all items in the inventory
         * @returns {string}
         */
        function getTotalWeight() {
            var sum = arrayUtil.sum($ctrl.inventory.items, function (item) {
                return item.weight * item.count;
            });
            return (Math.round(sum * 100) / 100).toFixed(2);
        }

        //endregion

    }

})();
