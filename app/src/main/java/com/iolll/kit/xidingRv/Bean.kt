package com.iolll.kit.xidingRv

class Title(
    var name:String,
    var isTop:Boolean ,// 是否可以在顶部滞留
    var isHeader:Boolean // 是否可以在Header滞留



) {
    override fun toString(): String {
        return "Title(name='$name', isTop=$isTop, isHeader=$isHeader)"
    }
}

class TitleChildren(
    var name:String
)

