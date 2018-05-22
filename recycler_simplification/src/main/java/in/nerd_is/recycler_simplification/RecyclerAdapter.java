package in.nerd_is.recycler_simplification;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xuqiang ZHENG on 18/4/19.
 */
public class RecyclerAdapter extends AbstractAdapter {

    protected List<Object> data = Collections.emptyList();

    public RecyclerAdapter(@NonNull RuleSet ruleSet) {
        super(ruleSet);
    }

    @Override
    public List<?> getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    public void swapData(List<?> list) {
        this.data = (List<Object>) list;
        notifyDataSetChanged();
    }

    public void insert(int position, Object item) {
        if (isDataEmpty()) {
            data = new ArrayList<>();
            data.add(item);
            notifyItemInserted(position);
        } else {
            data.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void insert(int position, List<?> list) {
        if (isDataEmpty()) {
            data = new ArrayList<>(list);
            notifyItemRangeInserted(0, list.size());
        } else {
            data.addAll(position, list);
            notifyItemRangeInserted(position, list.size());
        }
    }

    public void append(Object item) {
        insert(data.size(), item);
    }

    public void append(List<?> list) {
        insert(data.size(), list);
    }

    public void appendToHead(Object item) {
        insert(0, item);
    }

    public void appendToHead(List<?> list) {
        insert(0, list);
    }

    public void update(int position, Object item) {
        data.set(position, item);
        notifyItemChanged(position);
    }

    public void update(int position, List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            data.set(position + i, list.get(i));
        }
        notifyItemRangeChanged(position, list.size());
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position, int itemCount) {
        ArrayList<Object> list = new ArrayList<>(data.size() - itemCount);
        list.addAll(data.subList(0, position));
        list.addAll(data.subList(position + itemCount, data.size()));
        data = list;
        notifyItemRangeRemoved(position, itemCount);
    }

    private boolean isDataEmpty() {
        return Collections.emptyList().equals(data);
    }
}
