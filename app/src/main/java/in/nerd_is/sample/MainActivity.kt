package `in`.nerd_is.sample

import `in`.nerd_is.recycler_simplification.RecyclerAdapter
import `in`.nerd_is.recycler_simplification.TypeFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import java.util.Random

class MainActivity : AppCompatActivity() {

  private val random = Random()
  private val adapter = RecyclerAdapter(SimpleTypeFactory())
  private var count = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    btnAddItem.setOnClickListener { adapter.append(generateData()) }
    btnAddToHead.setOnClickListener { adapter.appendToHead(generateData()) }

    recyclerView.layoutManager = LinearLayoutManager(this)
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

  private class SimpleTypeFactory : TypeFactory() {
    override fun addTypeRules() {
      add(SimpleTypeRule())
    }
  }

}
