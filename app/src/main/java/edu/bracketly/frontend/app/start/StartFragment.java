package edu.bracketly.frontend.app.start;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.github.aakira.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;


public class StartFragment extends BaseFragment<StartFragmentPresenter> {

    @BindView(R.id.live_matches_header)
    View liveMatchesHeader;

    @BindView(R.id.live_matches_expendable)
    ExpandableLayout liveMatchesExpandable;

    @BindView(R.id.live_matches_drop_down)
    ImageView liveMatchesDropDown;

    @BindView(R.id.list)
    RecyclerView list;

    private Unbinder unbinder;

    public StartFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.live_matches_header)
    public void onLiveMatchesHeaderClick(View view) {
        if (liveMatchesExpandable.isExpanded()) {
            RotateAnimation rotate = new RotateAnimation(0, -90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setInterpolator(new LinearInterpolator());
            rotate.setFillAfter(true);
            liveMatchesDropDown.startAnimation(rotate);
            liveMatchesExpandable.collapse();
        } else {
            RotateAnimation rotate = new RotateAnimation(-90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            rotate.setInterpolator(new LinearInterpolator());

            liveMatchesDropDown.startAnimation(rotate);
            liveMatchesExpandable.expand();
        }
    }

    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.log_out_button)
    void onLogOutClick() {
        presenter.onLogOutButtonClick();
    }

    public void notifyDataSetChanged() {
        if (list.getAdapter() != null) {
            list.getAdapter().notifyDataSetChanged();
        }
    }

    public void logOut() {
        getActivity().finish();
    }
}
