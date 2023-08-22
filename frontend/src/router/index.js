import Vue from 'vue'
import Router from 'vue-router'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import OrderView from "@/views/OrderView";
import ChooseView1 from "@/views/ChooseView1"
import ChooseView2 from "@/views/ChooseView2"
import test from "@/views/test"
import TestView from "@/views/TestView";
import Nav from "@/views/HomePage/Nav";
import PaymentView from "@/views/PaymentView";
// import History from "@/views/HistoryView";
import Plan from "@/views/PlanHeaderView"
import ComboView from "@/views/ComboView";



Vue.use(Router)

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
    {
        path: '/',
        redirect: "/home/homepage"
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView, // () => import('@/views/LoginView.vue'),
        hidden: true
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterView,
    },
    {
        path: '/combo',
        name: 'combo',
        component: ComboView,

    },
    {
        path: '/payment',
        name: 'payment',
        component: PaymentView,
    },

    {
        path: '/home',
        redirect: "/home/homepage",
        name: 'home',
        component: Nav,
        children: [
            {
                path: 'homepage',
                name: "HomePage",
                component: () => import("../views/HomeView")
            },
            {
                path: 'plan',
                name: "PlanPage",
                component: () => import("../views/PlanHeaderView")
            },
            {
                path: 'testpage',
                name: "TestPage",
                component: () => import("../views/TestView")
            },

        ]
    },
    // {
    //     path: '/order',
    //     name: 'order',
    //     component: OrderView
    // },
    {
      path: '/choose1',
      name: 'choose1',
      component: ChooseView1
    },
    {
      path: '/choose2',
      name: 'choose2',
      component: ChooseView2
    },
    {
        path: '/profile',
        name: 'profile',
        component: () => import("../views/ProfileView")
    },
    {
      path: '/test',
      name: 'test',
      component: TestView //test
    },
    {
        path: '/about',
        name: 'about',
        component: () => import("../views/AboutView")
    },
    {
        path: '/policy',
        name: 'policy',
        component: () => import("../views/PolicyView")
    },
    {
        path: '/plan',
        name: 'PlanView',
        component: Plan
    }

]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({y: 0}),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router


