package shaadi.com.matches

import dagger.Subcomponent
import shaadi.com.di.ActivityScope


@ActivityScope
@Subcomponent
interface MatchListComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : MatchListComponent
    }

    //Need to find classes those can be injected by this component
    fun inject(mainActivity : MatchListActivity)
}