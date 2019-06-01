/*
 *    Copyright 2018 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package in.nerd_is.recycler_simplification;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Xuqiang ZHENG on 18/4/21.
 */
public class AdapterDelegate {

    private RuleSet ruleSet;
    private HasListData data;

    public AdapterDelegate(RuleSet ruleSet, HasListData data, RecyclerView.Adapter adapter) {
        this.ruleSet = ruleSet;
        this.data = data;
        this.ruleSet.setup(data, adapter);
    }

    public int getItemViewType(int position) {
        return ruleSet.getType(data.getData().get(position).getClass());
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ruleSet.createViewHolder(parent, viewType);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ruleSet.bindViewHolder(holder, data.getData().get(position));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        ruleSet.bindViewHolder(holder, data.getData().get(position), payloads);
    }
}
