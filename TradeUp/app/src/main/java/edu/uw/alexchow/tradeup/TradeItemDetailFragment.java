package edu.uw.alexchow.tradeup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

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

        final View rootView;
        // Show the dummy content as text in a TextView.
        if (addActivity) {
            rootView = inflater.inflate(R.layout.tradeitem_add, container, false);
            Button btnSubmit = (Button) rootView.findViewById(R.id.add_submit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText description = (EditText) rootView.findViewById(R.id.add_description);
                    EditText id = (EditText) rootView.findViewById(R.id.add_id);
                    EditText name = (EditText) rootView.findViewById(R.id.add_name);
                    EditText posterName = (EditText) rootView.findViewById(R.id.add_posterName);
                    EditText status = (EditText) rootView.findViewById(R.id.add_status);
                    EditText timeStamp = (EditText) rootView.findViewById(R.id.add_timeStamp);


                    TradeItem newItem = new TradeItem();
                    newItem.setDescription(description.getText().toString());
                    newItem.setId(id.getText().toString());
                    newItem.setName(name.getText().toString());
                    newItem.setPosterName(posterName.getText().toString());
                    newItem.setStatus(status.getText().toString());
                    newItem.setTimeStamp(timeStamp.getText().toString());



                    new Firebase("https://project-5593274257047173778.firebaseio.com/items")
                            .push().setValue(newItem);

                    Toast.makeText(getContext(), "Item posted successfully!", Toast.LENGTH_SHORT).show();

                    Context context = view.getContext();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);

                }
            });

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
