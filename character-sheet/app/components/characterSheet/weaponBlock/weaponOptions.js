/**
 * @constructor
 * @property {Die} die
 * @property {number} modifier
 * @property {string} statType
 */
function WeaponOptions() {
    this.die = new Die();
    this.modifier = 0;
    this.statType = statTypes.STR;
}