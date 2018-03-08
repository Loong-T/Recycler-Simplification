package `in`.nerd_is.sample

import `in`.nerd_is.recycler_simplification.RecyclerAdapter
import `in`.nerd_is.recycler_simplification.TypeFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

  private val adapter = RecyclerAdapter(SimpleTypeFactory())
  private var count = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    adapter.swap(generateData().toMutableList())
  }

  private fun generateData(): List<SimpleData> =
    count.until(count + 20)
      .map { SimpleData("Simple data $it") }
      .toList()

  private class SimpleTypeFactory : TypeFactory() {
    override fun addTypeRules() {
      add(SimpleTypeRule())
    }
  }

}
