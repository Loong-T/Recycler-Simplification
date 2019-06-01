/*
 * Copyright 2017 Xuqiang ZHENG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.nerd_is.recycler_simplification;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Xuqiang ZHENG on 2017/2/25.
 */
public class RuleSet {

    public static class Builder {

        private RuleSet ruleSet = new RuleSet();

        public Builder() {
        }

        public Builder add(TypeRule rule) {
            ruleSet.add(rule);
            return this;
        }

        public RuleSet build() {
            return ruleSet;
        }
    }

    private final List<Class<?>> classes = new ArrayList<>();
    private final SparseArray<TypeRule> rules = new SparseArray<>();

    private RuleSet() {
    }

    protected void add(TypeRule rule) {
        classes.add(rule.getDataClass());
        rules.put(getLastIndex(), rule);
    }

    public int getType(Class<?> clazz) {
        int idx = classes.indexOf(clazz);
        if (idx >= 0) {
            return idx;
        }

        for (int i = 0, classesSize = classes.size(); i < classesSize; i++) {
            if (classes.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Unknown class: " + clazz.getCanonicalName());
    }

    public ViewHolder createViewHolder(ViewGroup parent, int type) {
        TypeRule rule = rules.get(type);
        return rule.createHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @SuppressWarnings("unchecked")
    public void bindViewHolder(ViewHolder viewHolder, Object item) {
        viewHolder.render(item);
    }

    public void bindViewHolder(ViewHolder viewHolder, Object item, List<Object> payloads) {
        bindViewHolder(viewHolder, item);
    }

    void setup(HasListData data, RecyclerView.Adapter adapter) {
        for (int i = 0; i < rules.size(); ++i) {
            TypeRule rule = rules.get(rules.keyAt(i));
            rule.hasListData = data;
            rule.adapter = adapter;
        }
    }

    private int getLastIndex() {
        return classes.size() - 1;
    }
}