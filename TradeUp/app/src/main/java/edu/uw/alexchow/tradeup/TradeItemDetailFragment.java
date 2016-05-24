package edu.uw.alexchow.tradeup;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uw.alexchow.tradeup.dummy.DummyContent;

/**
 * A fragment representing a single TradeItem detail screen.
 * This fragment is either contained in a {@link TradeItemListActivity}
 * in two-pane mode (on tablets) or a {@link TradeItemDetailActivity}
 * on handsets.
 */
public class TradeItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TradeItem mItem;
    private boolean addActivity = false;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TradeItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            if (getArguments().getString(ARG_ITEM_ID).equals("activityMainAdd")) {
                addActivity = true;
                mItem = new TradeItem();
                mItem.setTitle("Create an Item");
            } else {
                mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            }

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        // Show the dummy content as text in a TextView.
        if (addActivity) {
            rootView = inflater.inflate(R.layout.tradeitem_add, container, false);
        } else if (mItem != null) {
            rootView = inflater.inflate(R.layout.tradeitem_detail, container, false);
            ((TextView) rootView.findViewById(R.id.trade_item_description)).setText(mItem.description);
            ((TextView) rootView.findViewById(R.id.tradeitem_id)).setText(mItem.id);
            ((TextView) rootView.findViewById(R.id.tradeitem_name)).setText(mItem.name);
            ((TextView) rootView.findViewById(R.id.tradeitem_posterName)).setText(mItem.posterName);
            ((TextView) rootView.findViewById(R.id.tradeitem_status)).setText(mItem.status);
            ((TextView) rootView.findViewById(R.id.tradeitem_timeStamp)).setText(mItem.timeStamp);
        } else {
            rootView = inflater.inflate(R.layout.tradeitem_detail, container, false);

        }
        return rootView;
    }
}
