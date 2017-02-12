/**
 * Created by Paul Parker on 2/12/2017.
 */

if (!String.prototype.format) {
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined' ? args[number] : match;
        });
    };
}

function Product(name, manufacturerMakeModel, condition, description, retailPrice, rummagePrice, productCode) {
    this.name = name;
    this.manufacturerMakeModel = manufacturerMakeModel;
    this.condition = condition;
    this.description = description;
    this.retailPrice = retailPrice;
    this.rummagePrice = rummagePrice;
    this.productCode = productCode;
}

Product.CONDITIONS = {
    NEW: "New",
    USED: "Used"
};

Product.prototype.getThumbnailUrl = function () {
    return "../images/{0}sm.png".format(this.productCode);
};

Product.prototype.getImageUrl = function () {
    return "../images/{0}.png".format(this.productCode);
};

Product.prototype.getFormattedRetailPrice = function () {
    return formatPrice(this.retailPrice);
};

Product.prototype.getFormattedRummagePrice = function () {
    return formatPrice(this.rummagePrice);
};

Product.prototype.isNew = function () {
    return !this.isUsed();
};

Product.prototype.isUsed = function () {
    return this.condition === Product.CONDITIONS.USED;
};

function formatPrice(price) {
    return "$" + price.toFixed(2);
}

var websiteInformation = {
    productCodeMap: {},
    products: [],
    queryInformation: {},

    /**
     * @param productCode
     * @returns {Product}
     */
    getProduct: function (productCode) {
        return this.productCodeMap[productCode] || null;
    },

    /**
     *
     * @returns {Product}
     */
    getRandomProduct: function () {
        var index = Math.floor(Math.random() * this.products.length);
        return this.products[index];
    },

    /**
     * @returns {Product}
     */
    getQueriedProduct: function () {
        var productCode = this.queryInformation["productCode"];
        if (!productCode) {
            return null;
        }

        return this.getProduct(productCode);
    }
};

(function () {

    function loadProducts() {
        var pens = new Product("Pens", "Pen Co., 12 pack, Fountain", Product.CONDITIONS.NEW, "Fountain pens in various colors", 2.99, 1.99, "product1");
        var pencils = new Product("Pencils", "Pen Co., 10 pack, HB", Product.CONDITIONS.NEW, "Standard HB pencils", 3.99, 1.99, "product2");
        var vellumPaper = new Product("Vellum Paper", "Surfaces R Us, 20 sheets, 30 weight", Product.CONDITIONS.NEW, "High quality vellum sheets suitable for calligraphy", 10.00, 7.99, "product3");
        var draftingDesk = new Product("Drafting Desk", "Second Chance Furniture, Standard, 3' x 4'", Product.CONDITIONS.USED, "Gently used adjustable drafting desk", 150.00, 100.00, "product4");
        var deskLamp = new Product("Desk Lamp", "Second Chance Furniture, Efficiency, 30 watt", Product.CONDITIONS.USED, "Gently used desk mountable lamp", 20.00, 12.99, "product5");

        websiteInformation.products = [pens, pencils, vellumPaper, draftingDesk, deskLamp];
        websiteInformation.products.forEach(function (product) {
            websiteInformation.productCodeMap[product.productCode] = product;
        })
    }

    function loadQueryInformation() {
        var myQs = location.search.substr(1);
        var queries = myQs.split("&");

        var queryInformation = {};
        queries.forEach(function (query) {
            splitQuery = query.split("=");
            queryInformation[splitQuery[0]] = splitQuery[1];
        });

        websiteInformation.queryInformation = queryInformation;
    }

    loadProducts();
    loadQueryInformation();

})();