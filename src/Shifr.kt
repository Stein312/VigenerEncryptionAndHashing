
import java.io.File
/*
Программа для шифрования.
Перед шифрование добавляется соль после используя методо вижнера кодируется.
В конце хешируется.
 */
val alf=genTable()      //создание алфавита
fun main(){
    val text= readLine() ?:return
    val key= readLine() ?:return
    val encryp=encrypt(addSalt(text),key)
    var en:String=""
    encryp.forEach {
        en += it.toString()
    }                                   // Преобразования из Array(char) в String
    val hashText=hashCrypt(en)      //хеширование
    println(hashText)
    //File("pass.txt").writeText(hashCrypt(en))
    val pas=File("pass.txt").bufferedReader().readLine()
    println(hashText == pas)

}
fun addSalt(text: String):String{               //добавление соли
    var saltText=text
    var i=' '
    while(saltText.length<32){
        saltText+=i
        i++
    }
    return saltText
}
fun hashCrypt(text: String):String{             //хеширование используя иднекс символа в алфавите
    var hashText:String=""
    for(i in text){
        hashText+=alf[alf.indexOf(i)%9]
    }
    return hashText
}
fun encrypt(text:String,key:String):Array<Char>{            //шифрование Виженера по формуле

    var encryptText=Array<Char>(text.length){i->
        alf[((alf.indexOf(text[i])+alf.indexOf(key[i-(key.length*(i/key.length))]))%alf.size)]
    }
    return encryptText
}
/*fun decrypt(text:String,key:String):String{             //дешифровака виженера для тестирования

    var decryptText=Array<Char>(text.length){i->
        alf[((alf.indexOf(text[i])+alf.size-alf.indexOf(key[i-(key.length*(i/key.length))]))%alf.size)]
    }
    return decryptText.joinToString (separator = " ")
}*/
fun genTable():Array<Char>{                             //генерация алфавита
    val arrAlf= Array<Char>(3000){i -> (' '+i) }
    return arrAlf
}