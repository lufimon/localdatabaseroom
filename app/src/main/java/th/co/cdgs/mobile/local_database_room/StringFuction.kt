package th.co.cdgs.mobile.local_database_room

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)