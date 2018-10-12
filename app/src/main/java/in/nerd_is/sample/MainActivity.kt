package `in`.nerd_is.sample

import `in`.nerd_is.recycler_simplification.RecyclerAdapter
import `in`.nerd_is.recycler_simplification.RuleSet
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import java.util.Random

class MainActivity : AppCompatActivity() {

  private val random = Random()
  private lateinit var adapter: RecyclerAdapter
  private var count = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    btnAddItem.setOnClickListener { adapter.append(generateData()) }
    btnAddToHead.setOnClickListener { adapter.appendToHead(generateData()) }

    adapter = RecyclerAdapter(RuleSet.Builder().add(SimpleTypeRule()).build())
    recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
    recyclerView.adapter = adapter
    adapter.swapData(generateDataList())
  }

  private fun generateData(): SimpleData {
    return SimpleData("Simple data ${random.nextInt(100)}")
  }

  private fun generateDataList(): List<SimpleData> {
    return count.until(count + 20)
        .map { SimpleData("Simple data $it") }
        .toList()
  }
}
