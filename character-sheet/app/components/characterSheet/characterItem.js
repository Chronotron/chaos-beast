/**
 * @constructor
 * @property {number} count
 * @property {number} weight
 * @property {string} desc
 * @property {string} name
 * @property {string} type
 */
function CharacterItem() {
    this.count = 0;
    this.desc = '';
    this.name = '';
    this.options = null;
    this.type = CharacterItem.types.MISC;
    this.weight = 0.1;
}

CharacterItem.types = {
    MISC: "Misc",
    WEAPON: "Weapon"
};

/**
 * @returns {Array}
 */
CharacterItem.getTypes = function () {
    return getEnumerationToArray(CharacterItem.types);
};
