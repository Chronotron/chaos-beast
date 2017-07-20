function CharacterItem() {
    this.count = 0;
    this.desc = '';
    this.name = '';
    this.type = CharacterItem.types.MISC;
    this.weight = 0.1;
}

CharacterItem.types = {
    MISC: "Misc",
    WEAPON: "Weapon"
};

CharacterItem.getTypes = function () {
    var types = [];
    for (var prop in CharacterItem.types) {
        if (!CharacterItem.types.hasOwnProperty(prop)) {
            continue;
        }
        types.push(CharacterItem.types[prop]);
    }
    return types
};
