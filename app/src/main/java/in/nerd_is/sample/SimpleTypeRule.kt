package `in`.nerd_is.sample

import `in`.nerd_is.recycler_simplification.TypeRule
import `in`.nerd_is.recycler_simplification.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Xuqiang ZHENG on 18/3/8.
 */
class SimpleTypeRule : TypeRule<SimpleData, SimpleHolder>(SimpleData::class.java),
  SimpleHolder.OnDeleteClickListener {

  override fun onDeleteClick(pos: Int) {
    adapter.data.removeAt(pos)
    adapter.notifyItemRemoved(pos)
  }

  override fun createHolder(inflater: LayoutInflater, parent: ViewGroup): SimpleHolder {
    return SimpleHolder(inflater.inflate(R.layout.main_recycler_item, parent, false), this)
  }
}

data class SimpleData(val data: String)

class SimpleHolder(view: View,
                   private val listener: OnDeleteClickListener) : ViewHolder<SimpleData>(view) {

  private val textView = view.findViewById<TextView>(R.id.textView)
  private val imageView = view.findViewById<ImageView>(R.id.imageView)

  override fun render(data: SimpleData) {
    textView.text = data.data
    imageView.setOnClickListener {
      listener.onDeleteClick(adapterPosition)
    }
  }

  interface OnDeleteClickListener {
    fun onDeleteClick(pos: Int)
  }
}