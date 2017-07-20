var arrayUtil = {

    removeObj: function (array, object) {
        var index = -1;
        for (var i = 0; i < array.length; i++) {
            if (array[i] === object) {
                index = i;
                break;
            }
        }

        array.splice(index, 1);
    },

    sum: function (array, opt_interpreter, initialValue) {
        initialValue = initialValue || 0;
        return array.reduce(function (total, num) {
            return (total || 0) + (opt_interpreter ? opt_interpreter(num) : num);
        }, initialValue);
    }

};

