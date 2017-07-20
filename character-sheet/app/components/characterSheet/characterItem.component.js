(function () {
    angular.module('characterSheet').component('characterItem', {
        controller: CharacterItemController,
        template: '' +
        '<span class="table-cell"><input class="short-text" ng-model="$ctrl.item.name" title="{{$ctrl.item.name}}" ng-maxlength="25"></span>' +
        '<span class="table-cell fill"><input class="med-text" ng-model="$ctrl.item.desc" title="{{$ctrl.item.desc}}" ng-maxlength="100"></span>' +
        '<span class="table-cell"><select ng-model="$ctrl.item.type" ng-options="itemType for itemType in $ctrl.itemTypes" ng-change="$ctrl.onTypeChange()"></select></span>' +
        '<span class="table-cell"><input type="number" class="xshort-text" ng-model="$ctrl.item.count" min="0" max="999" step="1"></span>' +
        '<span class="table-cell"><input type="number" class="xshort-text" ng-model="$ctrl.item.weight" min="0" max="999" step="0.01"></span>' +
        '<span class="table-cell">' +
        '<button type="button" class="remove-btn" ng-click="$ctrl.removeItem()">X</button>' +
        '</span>',
        bindings: {
            inventory: '=',
            item: '='
        }
    });

    function CharacterItemController() {
        var $ctrl = this;

        //region Public Interface

        $ctrl.itemTypes = CharacterItem.getTypes();

        $ctrl.onTypeChange = onTypeChange;
        $ctrl.removeItem = removeItem;

        //endregion

        //region Behavior

        /**
         * configures options when type changes
         */
        function onTypeChange() {
            var item = $ctrl.item;
            if (!item.type === CharacterItem.types.WEAPON) {
                item.options = null;
                return;
            }
            item.options = new WeaponOptions();
        }

        function removeItem() {
            if (!$ctrl.inventory) {
                return;
            }

            arrayUtil.removeObj($ctrl.inventory.items, $ctrl.item)
        }

        //endregion
    }

})();
