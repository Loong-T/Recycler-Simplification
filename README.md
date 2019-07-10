# recycler-simplification

![Latest release](https://img.shields.io/github/tag/Loong-T/recycler-simplification.svg)

## Usage

Add the JitPack repository to your root build file:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add recycler-simplification to your dependencies:
```groovy
dependencies {
    implementation 'in.nerd-is:recycler-simplification:0.2.4'
}
```

Declare a ViewHolder like RecycleView.ViewHoler:
```kotlin
data class SimpleData(val data: String)

class SimpleHolder(
  view: View,
  private val listener: OnDeleteClickListener
) : ViewHolder<SimpleData>(view) {

  private val textView = view.findViewById<TextView>(R.id.textView)
  private val imageView = view.findViewById<ImageView>(R.id.imageView)

  // bind your data to UI in this method
  override fun render(data: SimpleData) {
    textView.text = data.data
  }
}
```

Create ViewHolder in TypeRule:
```kotlin
class SimpleTypeRule : TypeRule<SimpleData, SimpleHolder>(SimpleData::class.java) {
  override fun createHolder(inflater: LayoutInflater, parent: ViewGroup): SimpleHolder {
    return SimpleHolder(inflater.inflate(R.layout.main_recycler_item, parent, false), this)
  }
}
```

If you want to add multi types of item into RecyclerView, create more TypeRule and add them to a RuleSet:

```kotlin
val ruleSet = RuleSet.Builder().add(SimpleTypeRule()).build()
```

Construct a RecyclerAdapter and set it to the RecyclerView:

```kotlin
recyclerView.adapter = RecyclerAdapter(ruleSet)
```

## More usage

### Load more

Use EndlessScrollListener to implement endless scroll list.

### Work with DiffUtil

```kotlin
open class DiffRecyclerAdapter<T>(
  ruleSet: RuleSet,
  diffCallback: DiffUtil.ItemCallback<T>
) : AbstractAdapter(ruleSet) {

  @Suppress("LeakingThis")
  private var differ: AsyncListDiffer<T> = AsyncListDiffer(this, diffCallback)

  override fun getData(): MutableList<*> {
    return differ.currentList
  }

  fun submitList(list: List<T>) {
    differ.submitList(list)
  }
}
```

### Work with PagedListAdapter
```kotlin
class PagingAdapter(ruleSet: RuleSet) :
  PagedListAdapter<DataType, ViewHolder>(DIFF_CALLBACK), HasListData {

  private val delegate = AdapterDelegate(ruleSet, this, this)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return delegate.onCreateViewHolder(parent, viewType)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    delegate.onBindViewHolder(holder, position)
  }

  override fun getData(): MutableList<*> {
    return currentList?.snapshot() ?: arrayListOf<Any>()
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataType>() {
      override fun areItemsTheSame(oldItem: DataType, newItem: DataType): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: DataType, newItem: DataType): Boolean {
        return oldItem == newItem
      }
    }
  }
}
```
