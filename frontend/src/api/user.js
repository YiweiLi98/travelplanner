import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function regsiter(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}


export function getInfo(token) {
  return request({
    url: '/user/info', // TODO: CHANGE IT
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

