package in.nerd_is.recycler_simplification;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Xuqiang ZHENG on 18/3/7.
 */
public abstract class TypeRule<T, VH extends ViewHolder> {

    protected RecyclerView.Adapter adapter;
    protected HasListData hasListData;

    private Class<T> dataClass;

    public TypeRule(Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    public Class<T> getDataClass() {
        return dataClass;
    }

    public abstract VH createHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);
}
