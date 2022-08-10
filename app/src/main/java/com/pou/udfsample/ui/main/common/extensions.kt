package com.pou.udfsample.ui.main.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.viewBinding(
    crossinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = true
) =
    bindingInflater.invoke(LayoutInflater.from(this.context), this, attachToParent)

inline fun <T : ViewBinding> View.viewBinding(
    crossinline bindingInflater: (LayoutInflater, View, Boolean) -> T,
    attachToParent: Boolean = true
) =
    bindingInflater.invoke(LayoutInflater.from(this.context), this, attachToParent)

inline fun <T : ViewBinding> Fragment.viewBinding(crossinline bindingInflater: (View) -> T) =
    bindingInflater.invoke(this.view!!)

fun RecyclerView.ViewHolder.getString(@StringRes id: Int): String {
    return itemView.context.getString(id)
}

fun RecyclerView.ViewHolder.getString(@StringRes id: Int, vararg items: Any): String {
    return itemView.context.getString(id, *items)
}

/*
private fun Context.popup(
    viewToAttach: View,
    arrayList: ArrayList<String>
) {
    val popupMenu = PopupMenu(sContext, viewToAttach)
    for (i in 0 until arrayList.size) {
        popupMenu.getMenu().add(i, Menu.FIRST, i, arrayList[i])
    }
    popupMenu.setOnMenuItemClickListener(object :
        PopupMenu.OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem): Boolean {
            textView.setText(item.getTitle())
            return false
        }
    })
    popupMenu.show()
}*/
