/**
 *
 * @param {number} count
 * @param {number} sides
 * @constructor
 * @property {number} count
 * @property {number} sides
 */
function Die(count, sides) {
    this.count = Math.ceil(count || 1);
    this.sides = Math.ceil(sides || 6);
}

Die.prototype.roll = function () {
    var total = 0;
    var min = 1;
    var max = Math.floor(this.sides);
    for (var i = 0; i < this.count; i++) {
        total += Math.floor(Math.random() * (max - min + 1)) + min;
    }
    return total;
};