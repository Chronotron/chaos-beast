var statModifiers = {
    /**
     * @param value
     * @return {number}
     */
    calculateMod: function (value) {
        if (!value) {
            return 0;
        }

        return Math.floor((value - 10) / 2);
    }
};