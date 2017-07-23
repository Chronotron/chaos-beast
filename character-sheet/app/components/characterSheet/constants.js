var statTypes = {
    STR: 'STR',
    DEX: 'DEX',
    CON: 'CON',
    INT: 'INT',
    WIS: 'WIS',
    CHA: 'CHA'
};

/**
 * @param enumeration
 * @returns {Array}
 */
function getEnumerationToArray(enumeration) {
    var types = [];
    for (var prop in enumeration) {
        if (!enumeration.hasOwnProperty(prop)) {
            continue;
        }
        types.push(enumeration[prop]);
    }
    return types
}

function getStatTypes() {
    return getEnumerationToArray(statTypes);
}