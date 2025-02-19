---
title: vuex -> Pinia 마이그레이션 트러블슈팅
excerpt: "\bvuex에서 pinia로 마이그레이션하는 과정에서 겪은 문제를 해결한 과정에 대해 작성하였습니다."
tags:
  - vue@3
  - javascript
  - pinia
toc: true
toc_sticky: true
date: 2025-02-19
last_modified_at: 2025-02-19
---

## 0.  개발 환경

- **프레임워크**: Vue 3
    
- **상태 관리**: VueX -> Pinia
    
- **언어**: JavaScript (Pure JS)
    
- **IDE**: IntelliJ IDEA

## 1. 문제 발생

Vuex에서 Pinia로 마이그레이션하는 과정에서,

- **Store 외부 컴포넌트에서 `state` 및 `actions` 호출이 불가능하였다.**
- `actions` 내부 메서드(`checkAuthState()`)를 `authStore.checkAuthState()`로 호출하면 IDE에서 `Unresolved function or method` 오류가 발생하였다.

## 2. 원인 분석

- Pinia는 Vuex와 달리 `state`를 `authStore.isAuthenticated`처럼 직접 접근해야 하지만, JS 환경에서는 IDE(IntelliJ)가 `defineStore`의 반환 타입을 정확하게 추론하지 못하였다.
- **Store를 import한 외부 컴포넌트에서 `actions`를 정상적으로 호출하지 못하는 문제가 발생하였다.**
- TypeScript에서는 `ReturnType<typeof useAuthStore>`로 리턴 값을 명시하면 컴포넌트 외부에서 정상적으로 활용이 가능하였다. 이를 참고해, JavaScript에서는 JSDoc으로 타입을 명시적으로 지정해 주었다.

## 3. 해결 방법

### ✅ **(1) JSDoc을 이용해 반환 타입을 명확히 선언**

```js
/**  
 * @typedef {import('pinia').StoreDefinition} AuthStore  
 */

/** @returns {AuthStore} */
export const useAuthStore = defineStore('auth', {
  state: () => ({
    ...
  }),
  actions: {
    /**  
	 * 인증 여부 확인  
	 * @returns {boolean} 인증 여부  
	 */
    async checkAuthState() {
     ...
    }
    ...
  }
});
```

이와 같이, `@returns {AuthStore}`를 추가해 IDE가 `useAuthStore()` 반환 타입을 정확하게 추론할 수 있도록 하였다.

### ✅ **(2) Store를 사용하는 컴포넌트에서 명확한 타입 지정**

```js
/** @type {AuthStore} */
const authStore = useAuthStore();
```

이와 같이 타입을 명시하면 **외부 컴포넌트에서 `authStore.checkAuthState()`를 호출할 때 발생하는 오류를 방지할 수 있다.**

### ✅ **(3) IDE 캐싱 문제 해결**

- `node_modules/.vite` 및 `node_modules/.cache` 삭제 후 `npm install` 을 재실행하였다.
- **IDE를 완전히 종료하고 다시 실행**하여 JSDoc 변경 사항이 반영되도록 하였다.

## 4.  결론

- **JS + Vue 3 + Pinia 사용 시, store 외부에서 state, actions 불러오기에 대해 IDE의 자동 완성 및 타입 추론이 불가능한 문제는 JSDoc 명시로 해결할 수 있다.**
- 타입 명시(`@type {AuthStore}`)를 추가하면 더 안정적인 코드 작성이 가능하고, 내부 메서드와 개별 속성 또한 타입 명시를 해 주어 더 안정적으로 코드를 작성할 수 있다.


