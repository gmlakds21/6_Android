package seunghee.helloandroid._02_24_02_BooksV1

class BookVO (var bid:String, var brand:String, var bkname:String, var author:String, var pubdate:String, var price: String, var regdate: String) {
/*
    var bid : String=""
    var bkname : String=""
    var author : String=""
    var brand : String=""
    var pubdate : String=""
    var price : String=""
    var regdate : String=""
*/

    constructor(bid:String, bkname:String, author:String)
            : this(bid, "", bkname, author, "", "", "") {

        }
    constructor(brand:String, bkname:String, author:String, pubdate:String, price:String )
            : this("", brand, bkname, author, pubdate, price, "") {

        }

}
