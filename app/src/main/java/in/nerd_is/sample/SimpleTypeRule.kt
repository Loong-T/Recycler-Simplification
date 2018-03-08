package `in`.nerd_is.sample

import `in`.nerd_is.recycler_simplification.TypeRule
import `in`.nerd_is.recycler_simplification.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Xuqiang ZHENG on 18/3/8.
 */
class SimpleTypeRule : TypeRule<SimpleData, SimpleHolder>(SimpleData::class.java) {

  override fun createHolder(inflater: LayoutInflater, parent: ViewGroup): SimpleHolder {
    return SimpleHolder(inflater.inflate(R.layout.main_recycler_item, parent, false))
  }
}

data class SimpleData(val data: String)

class SimpleHolder(view: View) : ViewHolder<SimpleData>(view) {

  override fun render(data: SimpleData) {
    val root = itemView as ViewGroup
    val textView = root.findViewById<TextView>(R.id.textView)

    textView.text = data.data
  }
}