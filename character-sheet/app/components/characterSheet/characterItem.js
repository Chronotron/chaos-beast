function CharacterItem() {
    this.count = 0;
    this.desc = '';
    this.name = '';
    this.type = CharacterItem.types.MISC;
    this.weight = 0.1;
    this.options = null;
}

CharacterItem.types = {
    MISC: "Misc",
    WEAPON: "Weapon"
};

CharacterItem.getTypes = function () {
    return getEnumerationToArray(CharacterItem.types);
};
