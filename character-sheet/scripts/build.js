var concat = require('../node_modules/concat-files/index');

concat([
    'app/util/util.js',
    'app/components/characterSheet/constants.js',
    'app/components/characterSheet/statModifiers.js',
    'app/components/characterSheet/characterItem.js',
    'app/components/characterSheet/die.js',
    'app/components/characterSheet/weaponOptions.js',
    'app/components/characterSheet/characterSheet.module.js',
    'app/components/characterSheet/characterSheet.service.js',
    'app/components/characterSheet/characterInfo.component.js',
    'app/components/characterSheet/characterInventory.component.js',
    'app/components/characterSheet/statBlock.component.js',
    'app/components/characterSheet/weaponBlock.component.js',
    'app/components/characterSheet/characterItem.component.js',
    'app/components/characterSheet/characterSheet.component.js'
], 'app/character-sheet.js', function (err) {
    if (err) {
        throw err;
    }
    console.log('done');
});