(function () {
    angular.module('characterSheet').component('characterItem', {
        controller: CharacterItemController,
        template: '' +
        '<span class="table-cell"><input ng-model="$ctrl.item.name" title="{{$ctrl.item.name}}"></span>' +
        '<span class="table-cell"><input ng-model="$ctrl.item.desc" title="{{$ctrl.item.desc}}"></span>' +
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

        $ctrl.removeItem = removeItem;

        function removeItem() {
            if (!$ctrl.inventory) {
                return;
            }

            arrayUtil.removeObj($ctrl.inventory.items, $ctrl.item)
        }
    }

})();
