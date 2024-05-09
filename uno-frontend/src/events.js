export function createEventContainer() {
    return {
        subscribers: [],
    };
}

export function createEvent(identifier, payload) {
    return {
        identifier,
        payload
    };
}

export function addSubscriber(eventContainer, subscriber) {
    return {
        ...eventContainer,
        subscribers: [...eventContainer.subscribers, subscriber]
    };
}

export function sendEvent(eventContainer, event) {
    const subscribers = eventContainer.subscribers;

    subscribers.forEach(callback => {
        callback(event);
    });
}