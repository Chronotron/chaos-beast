(function () {
    angular.module('characterSheet').component('characterInventory', {
        controller: CharacterInventoryController,
        template: '' +
        '<div class="inventory-controls">' +
        '<button class="add-btn" type="button" ng-click="$ctrl.addItem()">+</button>' +
        '</div>' +
        '<div class="character-inventory table">' +
        '<character-item class="table-row" ng-repeat="item in $ctrl.inventory.items" item="item" inventory="$ctrl.inventory"></character-item>' +
        '<div class="table-caption">Total Weight: {{$ctrl.getTotalWeight()}}</div>' +
        '</div>',
        bindings: {
            inventory: '='
        }
    });

    function CharacterInventoryController() {
        var $ctrl = this;

        $ctrl.addItem = addItem;
        $ctrl.getTotalWeight = getTotalWeight;

        function addItem() {
            $ctrl.inventory.items.unshift(new CharacterItem());
        }

        function getTotalWeight() {
            return arrayUtil.sum($ctrl.inventory.items, function (item) {
                return item.weight * item.count;
            });
        }

    }

})();
