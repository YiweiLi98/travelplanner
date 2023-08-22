import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    // user: {},
    userId: 0,
    startDay: undefined,
    endDay: undefined,
    home: '',
    des: '',
    budget: '',
    style: {},
    day:'',
    price:'',
    group:'',
    mode:''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    console.log(name)
    state.name = name
  },
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_START: (state, startDay) => {
    state.startDay = startDay
  },
  SET_END: (state, endDay) => {
    state.endDay = endDay
  },
  SET_HOME: (state, home) => {
    state.home = home
  },
  SET_DES: (state, des) => {
    state.des = des.toLowerCase();
  },
  SET_BUDGET: (state, budget) => {
    state.budget = budget
  },
  SET_STYLE: (state, style) => {
    state.style = style
    console.log(style[1])
    // DEBUG : type of style: console.log(style[1])
  }
  ,
  SET_DAY: (state, day) => {
    state.day = day
  },
  SET_PRICE: (state, price) => {
    state.price = price
  },
  SET_GROUP: (state, group) => {
    state.group = group
  },
  SET_MODE: (state, mode) => {
    state.mode = mode
  },
}

const actions = {
  // user login
  login({ commit }, userInfo) { // i.e. commit.('login')
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_NAME', data.username)
        commit('SET_AVATAR', data.avatar)
        commit('SET_TOKEN', data.token)
        setToken(data.token)  // put in cookies
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) {
          return reject('Verification failed, please Login again.')
        }
        const { name, avatar } = data
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        console.log("DEBUGGING user.js/store: token now: "+ getToken())
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  },

  // store user choose 1
  storeChoose1( { commit} , choose) {
    const { home, destination, startDate,endDate,budget,group} = choose
    return new Promise((resolve) => {
      commit('SET_HOME', home)
      // console.log("DEBUGGING: stored home city is " + state.home)
      commit('SET_DES', destination)
      commit('SET_START', startDate)
      commit('SET_END', endDate)
      commit('SET_BUDGET', budget)
      commit('SET_GROUP', group)
      console.log(budget)
      commit('SET_DAY', (new Date(endDate)-new Date(startDate))/(24*60*60*1000)+1)
      resolve()
    })
  },

  // store user choose 2
  storeChoose2( { commit}, form) {
    const { attraction, tour, food, water} = form;
    const ls = []
    if(attraction) { ls[ls.length] = "nature"}
    if(tour) { ls[ls.length] =  "art"}
    if(food) { ls[ls.length] = "food"}
    if(water) { ls[ls.length] = "sport"}
    return new Promise((resolve) => {
      commit('SET_STYLE', ls)
      // DEBUG: console.log(state.style)
      resolve()
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

