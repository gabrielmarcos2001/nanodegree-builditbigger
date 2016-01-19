package gabilam.com.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeFragment extends Fragment {

    private View mImageView;
    private TextView mJokeTextView;

    public JokeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_joke, container, false);

        mJokeTextView = (TextView)root.findViewById(R.id.joke_textview);
        mImageView = root.findViewById(R.id.joke_image);
        mImageView.setVisibility(View.INVISIBLE);

        Intent i = getActivity().getIntent();
        String joke = i.getStringExtra(JokeActivity.JOKE_KEY);

        if (joke != null && !joke.isEmpty()) {
            mJokeTextView.setText(joke);
        }

        startAnimations();

        return root;
    }

    private void startAnimations() {

        Animation showAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom);
        mImageView.setVisibility(View.VISIBLE);
        mImageView.startAnimation(showAnim);
    }
}
