package com.moonx.o_sipher

import android.util.Log

class Cipher {
    private lateinit var string: String
    private var index = 0
    private val O = 'O'
    private val X = 'Ο'
    private val SPACE = 'О'

    fun encrypt(str: String) = buildString {
        for (char in str) {
            var thereEror = false
            when (char) {
                'ا', 'إ', 'أ', 'آ' -> append(O)
                'ب' -> append(X)
                'ت' -> append(O).append(O)
                'ث' -> append(O).append(X)
                'ج' -> append(X).append(O)
                'ح' -> append(X).append(X)
                'خ' -> append(O).append(O).append(O)
                'د' -> append(O).append(O).append(X)
                'ذ' -> append(O).append(X).append(O)
                'ر' -> append(O).append(X).append(X)
                'ز' -> append(X).append(O).append(X)
                'س' -> append(O).append(O).append(O).append(O)
                'ش' -> append(O).append(O).append(O).append(X)
                'ص' -> append(O).append(O).append(X).append(O)
                'ض' -> append(O).append(O).append(X).append(X)
                'ط' -> append(O).append(X).append(O).append(O)
                'ظ' -> append(O).append(X).append(O).append(X)
                'ع' -> append(O).append(X).append(X).append(O)
                'غ' -> append(O).append(X).append(X).append(X)
                'ف' -> append(X).append(O).append(O)
                'ق' -> append(X).append(X).append(O)
                'ك' -> append(X).append(X).append(X)
                'ل' -> append(X).append(O).append(X).append(X)
                'م' -> append(X).append(O).append(X).append(O)
                'ن' -> append(X).append(O).append(O).append(X)
                'ه' -> append(X).append(O).append(O).append(O)
                'و', 'ؤ' -> append(X).append(X).append(O).append(X)
                'ي', 'ئ' -> append(X).append(X).append(O).append(O)
                'ء' -> append(X).append(X).append(X).append(X)
                'ة' -> append(X).append(X).append(X).append(O)
                ' ' -> append(O).append(O).append(O).append(O).append(O)
                else -> {
                    thereEror = true
                }
            }
            if (thereEror.not())
                append(SPACE)
        }
    }

    fun decrypt(str: String) = buildString {
        this@Cipher.string = str
        while (index < string.length) {
            if (string[index] == O)
                append(state1())
            else if (string[index] == X)
                append(state2())
            index++
            val TAG = "1234"
            Log.d(TAG, "decrypt: " + index)
        }
    }

    fun state1(): Char {
        return when (string[++index]) {
            SPACE -> 'ا'
            O -> state3()
            X -> state4()
            else -> '*'
        }
    }

    fun state2(): Char {
        return when (string[++index]) {
            SPACE -> 'ب'
            O -> state5()
            X -> state6()
            else -> '*'
        }
    }

    fun state3(): Char {
        return when (string[++index]) {
            SPACE -> 'ت'
            O -> state7()
            X -> state8()
            else -> '*'
        }
    }

    fun state4(): Char {
        return when (string[++index]) {
            SPACE -> 'ث'
            O -> state9()
            X -> state10()
            else -> '*'
        }
    }

    fun state5(): Char {
        return when (string[++index]) {
            SPACE -> 'ج'
            O -> state12()
            X -> state11()
            else -> '*'
        }
    }

    fun state6(): Char {
        return when (string[++index]) {
            SPACE -> 'ح'
            O -> state13()
            X -> state14()
            else -> '*'
        }
    }

    fun state7(): Char {
        return when (string[++index]) {
            SPACE -> 'خ'
            O -> state15()
            X -> state16()
            else -> '*'
        }
    }

    fun state8(): Char {
        return when (string[++index]) {
            SPACE -> 'د'
            O -> state17()
            X -> state18()
            else -> '*'
        }
    }

    fun state9(): Char {
        return when (string[++index]) {
            SPACE -> 'ذ'
            O -> state19()
            X -> state20()
            else -> '*'
        }
    }

    fun state10(): Char {
        return when (string[++index]) {
            SPACE -> 'ر'
            O -> state21()
            X -> state22()
            else -> '*'
        }
    }

    fun state11(): Char {
        return when (string[++index]) {
            SPACE -> 'ز'
            O -> state24()
            X -> state23()
            else -> '*'
        }
    }

    fun state12(): Char {
        return when (string[++index]) {
            SPACE -> 'ف'
            O -> state26()
            X -> state25()
            else -> '*'
        }
    }

    fun state13(): Char {
        return when (string[++index]) {
            SPACE -> 'ق'
            O -> state28()
            X -> state27()
            else -> '*'
        }
    }

    fun state14(): Char {
        return when (string[++index]) {
            SPACE -> 'ك'
            O -> state30()
            X -> state29()
            else -> '*'
        }
    }

    fun state15(): Char {
        return when (string[++index]) {
            SPACE -> 'س'
            O -> state31()
            else -> '*'
        }
    }

    fun state16(): Char {
        return when (string[++index]) {
            SPACE -> 'ش'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state17(): Char {
        return when (string[++index]) {
            SPACE -> 'ص'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state18(): Char {
        return when (string[++index]) {
            SPACE -> 'ض'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state19(): Char {
        return when (string[++index]) {
            SPACE -> 'ط'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state20(): Char {
        return when (string[++index]) {
            SPACE -> 'ظ'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state21(): Char {
        return when (string[++index]) {
            SPACE -> 'ع'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state22(): Char {
        return when (string[++index]) {
            SPACE -> 'غ'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state23(): Char {
        return when (string[++index]) {
            SPACE -> 'ل'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state24(): Char {
        return when (string[++index]) {
            SPACE -> 'م'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state25(): Char {
        return when (string[++index]) {
            SPACE -> 'ن'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state26(): Char {
        return when (string[++index]) {
            SPACE -> 'ه'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state27(): Char {
        return when (string[++index]) {
            SPACE -> 'و'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state28(): Char {
        return when (string[++index]) {
            SPACE -> 'ي'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state29(): Char {
        return when (string[++index]) {
            SPACE -> 'ء'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state30(): Char {
        return when (string[++index]) {
            SPACE -> 'ة'
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }

    fun state31(): Char {
        return when (string[++index]) {
            SPACE -> ' '
//            O -> state()
//            X -> state()
            else -> '*'
        }
    }
}